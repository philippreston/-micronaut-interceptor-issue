package micronaut.interceptor.issue

import groovy.util.logging.Slf4j
import io.micronaut.aop.InvocationContext
import io.micronaut.http.HttpAttributes
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpRequest
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.ClientFilterChain
import io.micronaut.http.filter.HttpClientFilter
import org.reactivestreams.Publisher

@Slf4j
@Filter("/**")
class TestFilter implements HttpClientFilter {

    @Override
    Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {


        InvocationContext context = (InvocationContext) request.getAttribute(HttpAttributes.INVOCATION_CONTEXT).orElse(null)
        if(!context) {
            log.error "Filter - No context found"
            return chain.proceed(request)
        }

        // Get the token from the context 
        String token = context.getAttribute(TestInterceptor.AUTH_KEY, String.class).orElse(null)
        if(token) {
            log.info "Filter - Found token from context -> ${token}"
            request.bearerAuth(token)
        }
        else {
            log.error "Filter - NO TOKEN ON CONTEXT - Interceptor Did Not Fire!!"
        }
        
        // Continue
        return chain.proceed(request)
    }

}
