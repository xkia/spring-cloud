package org.xkia.grpc;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptor;
import io.grpc.ServerInterceptors;
import io.grpc.ServerServiceDefinition;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.type.StandardMethodMetadata;
import org.xkia.grpc.annotation.GRpcGlobalInterceptor;
import org.xkia.grpc.annotation.GRpcService;

/**
 * @author xkia
 * @data 2018/11/18
 */
@Slf4j
public class GRpcServerRunner implements CommandLineRunner, DisposableBean {

  @Autowired
  private AbstractApplicationContext applicationContext;

  private Server server;

  @Autowired
  private GRpcServerProperties gRpcServerProperties;

  private List<String> serviceNameList = new ArrayList<>();

  @Override
  public void destroy() {

    log.info("shutting down grpc service ....");
    Optional.ofNullable(server).ifPresent(Server::shutdown);
    log.info("grpc server stopped.");
  }

  @Override
  public void run(String... args) throws Exception {

    Collection<ServerInterceptor> globalInterceptors = getBeanNamesByTypeWithAnnotation(
        GRpcGlobalInterceptor.class, ServerInterceptor.class)
        .map(name -> applicationContext.getBeanFactory().getBean(name, ServerInterceptor.class))
        .collect(Collectors.toList());

    final ServerBuilder<?> serverBuilder = ServerBuilder.forPort(gRpcServerProperties.getPort());

    getBeanNamesByTypeWithAnnotation(GRpcService.class, BindableService.class).forEach(
        name -> {
          BindableService bs = applicationContext.getBeanFactory()
              .getBean(name, BindableService.class);
          ServerServiceDefinition serviceDefinition = bs.bindService();
          GRpcService gRpcService = applicationContext
              .findAnnotationOnBean(name, GRpcService.class);
          serviceDefinition = bindInterceptors(serviceDefinition, gRpcService, globalInterceptors);
          serverBuilder.addService(serviceDefinition);
          String serviceClassName = bs.getClass().getName();
          processServiceName(serviceClassName);
        }
    );
  }

  private <T> Stream<String> getBeanNamesByTypeWithAnnotation(
      Class<? extends Annotation> annotationType, Class<T> beanType) throws Exception {

    return Stream.of(applicationContext.getBeanNamesForType(beanType))
        .filter(name -> {
          BeanDefinition beanDefinition = applicationContext.getBeanFactory()
              .getBeanDefinition(name);
          if (beanDefinition.getSource() instanceof StandardMethodMetadata) {
            StandardMethodMetadata metadata = (StandardMethodMetadata) beanDefinition.getSource();
            return metadata.isAnnotated(annotationType.getName());
          }
          return null != applicationContext.getBeanFactory()
              .findAnnotationOnBean(name, annotationType);
        });
  }

  private ServerServiceDefinition bindInterceptors(ServerServiceDefinition serviceDefinition,
      GRpcService gRpcService,
      Collection<ServerInterceptor> globalInterceptors) {

    Stream<? extends ServerInterceptor> privateInterceptors = Stream.of(gRpcService.interceptors())
        .map(interceptorClass -> {
          try {
            return 0 < applicationContext.getBeanNamesForType(interceptorClass).length ?
                applicationContext.getBean(interceptorClass) :
                interceptorClass.newInstance();
          } catch (Exception e) {
            throw new BeanCreationException("Failed to create interceptor instance.", e);
          }
        });

    List<ServerInterceptor> interceptors = Stream.concat(
        gRpcService.applyGlobalInterceptors() ? globalInterceptors.stream() : Stream.empty(),
        privateInterceptors)
        .distinct()
        .collect(Collectors.toList());
    return ServerInterceptors.intercept(serviceDefinition, interceptors);
  }

  private void startDaemonAwaitThread() {

    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
        .setNameFormat("demo-pool-%d").build();
    ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory,
        new ThreadPoolExecutor.AbortPolicy());
    singleThreadPool.execute(() -> {
      try {
        GRpcServerRunner.this.server.awaitTermination();
      } catch (InterruptedException e) {
        log.error("gRPC stopped ", e);
      }
    });
  }

  private void processServiceName(String serviceName) {
    if (serviceName.contains("$$")) {
      serviceName = StringUtils.substringBefore(serviceName, "$$");
    }
    if (!StringUtils.isEmpty(serviceName)) {
      serviceName = StringUtils.substringAfterLast(serviceName, ".");
      serviceNameList.add(serviceName);
    }
  }

  public List<String> getServiceNameList() {
    return serviceNameList;
  }

}
