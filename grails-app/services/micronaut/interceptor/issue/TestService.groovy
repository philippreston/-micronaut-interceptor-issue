package micronaut.interceptor.issue

import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j
import io.micronaut.http.client.exceptions.HttpClientResponseException
import org.springframework.beans.factory.annotation.Autowired
import reactor.core.scheduler.Schedulers

import java.time.Duration

@Slf4j
@Transactional
class TestService {

    @Autowired
    TestClient client

    def request() {
        log.info "Service - calling backend service"
        client.endpoint()
              .map {
                  log.info "Service - Received from backend: ${it.toString()}"
                  it
              }
              .onErrorResume(HttpClientResponseException) {
                    log.error("Service - received HTTP/${it.status.code}")
              }
              .subscribeOn(Schedulers.immediate())
              .block(Duration.ofSeconds(60))
    }

}
