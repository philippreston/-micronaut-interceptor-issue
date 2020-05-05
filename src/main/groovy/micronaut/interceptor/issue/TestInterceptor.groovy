package micronaut.interceptor.issue

import groovy.util.logging.Slf4j
import io.micronaut.aop.MethodInterceptor
import io.micronaut.aop.MethodInvocationContext

import javax.inject.Singleton

@Slf4j
@Singleton
class TestInterceptor implements MethodInterceptor<Object, Object> {

    final public static String AUTH_KEY = "AUTH"

    @Override
    Object intercept(MethodInvocationContext<Object, Object> context) {
        log.trace("Interceptor - Adding dummy token to context")
        context.setAttribute(AUTH_KEY, "1234567890123456789001234567890")
        return context.proceed()
    }
}
