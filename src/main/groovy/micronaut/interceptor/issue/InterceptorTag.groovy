package micronaut.interceptor.issue

import io.micronaut.aop.Around
import io.micronaut.context.annotation.Type

import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.Target

import static java.lang.annotation.RetentionPolicy.RUNTIME

@Documented
@Retention(RUNTIME)
@Target([ElementType.METHOD, ElementType.TYPE])
@Around
@Type(TestInterceptor.class)
@interface InterceptorTag {

}
