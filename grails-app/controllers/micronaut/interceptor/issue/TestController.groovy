package micronaut.interceptor.issue

import groovy.util.logging.Slf4j
import org.springframework.http.HttpStatus

@Slf4j
class TestController {

    TestService testService

    /**
     * External facing endpoint representing a call from Grails
     * @return
     */
    def request() {
        log.info "Controller - Calling External Request"
        respond testService.request()
    }

    /**
     * This controller endpoint represents a backend microservice - but just included
     * here as and endpoint for the #{@link TestClient} allowing this example to be self
     * contained
     * @return
     */
    def endpoint() {
        String token = request.getHeader('Authorization')
        log.info "Dummy Microservice Controller - Getting Object (Token: ${token})"

        // No token - throw a 401
        if(!token) {
            render status: HttpStatus.UNAUTHORIZED
            return
        }

        // If there is a token - return an object
        respond new TestDto(id: "1qaz2wsx3edc", value: "Hello World")
    }
}
