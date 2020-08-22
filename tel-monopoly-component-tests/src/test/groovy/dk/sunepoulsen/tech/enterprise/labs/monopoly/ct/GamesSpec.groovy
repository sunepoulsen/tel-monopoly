package dk.sunepoulsen.tech.enterprise.labs.monopoly.ct

import dk.sunepoulsen.tech.enterprise.labs.core.component.tests.specification.DeploymentSpecification
import dk.sunepoulsen.tech.enterprise.labs.core.component.tests.verification.HttpResponseVerificator

import java.net.http.HttpRequest

class GamesSpec extends DeploymentSpecification {

    private static String CONTAINER_NAME = 'tel-monopoly-service'

    void setupSpec() {
        initDeployment('ct', [CONTAINER_NAME])
    }

    void "POST /games returns 202"() {
        given: 'Monopoly service is available'
            deployment.waitForAvailable(CONTAINER_NAME)

        when: 'Calculation random float'
            Float f = new Random().nextFloat()

        then: 'Check random float is positive and less than 1'
            f > 0 && f < 1
    }

    void "GET /unknown-path returns 404"() {
        given: 'HelloWorld service is available'
            deployment.waitForAvailable(CONTAINER_NAME)

        when: 'Call GET /unknown-path'
            HttpRequest httpRequest = httpHelper.newRequestBuilder(CONTAINER_NAME, '/unknown-path')
                .GET()
                .build()

            HttpResponseVerificator verificator = httpHelper.sendRequest(httpRequest)

        then: 'Response Code is 404'
            verificator.responseCode(404)

        and: 'Content Type is application/json'
            verificator.contentTypeIsJson()

        and: 'Response body is json'
            verificator.bodyIsJson()

        and: 'Verify greetings body'
            verificator.bodyAsJson() == [
                'timestamp': verificator.bodyAsJson().timestamp,
                'status': 404,
                'error': 'Not Found',
                'message': '',
                'path': '/unknown-path'
            ]
    }
}
