package org.xkia.grpc.annotation;

import io.grpc.ServerInterceptor;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Service;

/**
 * @author xkia
 * @data 2018/11/18
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface GRpcService {

  /**
   * @return protoc-generated class that creates {@link io.grpc.ServerServiceDefinition} via static
   * <code>bindService</code> function.
   */
  Class<?> grpcServiceOuterClass() default Object.class;

  Class<? extends ServerInterceptor>[] interceptors() default {};

  boolean applyGlobalInterceptors() default true;
}
