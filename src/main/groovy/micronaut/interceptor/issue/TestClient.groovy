package micronaut.interceptor.issue

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import reactor.core.publisher.Mono

@InterceptorTag
@Client("http://127.0.0.1:8080/test")
interface TestClient {

    @Get("/endpoint")
    Mono<TestDto> endpoint();
}
