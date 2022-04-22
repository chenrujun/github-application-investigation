package chenrujun.github.application.investigation.controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
public class GitHubController {

    private final WebClient webClient;

    public GitHubController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/github/user/orgs")
    public String userOrgs(@RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient githubClient) {
        return webClient
                .get()
                .uri("https://api.github.com/user/orgs")
                .attributes(oauth2AuthorizedClient(githubClient))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @GetMapping("/github/user/repos")
    public String getUserRepos(@RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient githubClient) {
        return webClient
                .get()
                .uri("https://api.github.com/user/repos")
                .attributes(oauth2AuthorizedClient(githubClient))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @GetMapping("/github/user/repos/create")
    public String PostUserRepos(@RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient githubClient) {
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("name","repository_created_by_github-application-investigation");
        return webClient
                .post()
                .uri("https://api.github.com/user/repos")
                .body(BodyInserters.fromValue(bodyMap))
                .attributes(oauth2AuthorizedClient(githubClient))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @GetMapping("/github/user/repos/put")
    public String putUserRepos(@RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient githubClient) {
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("message","commit by github-application-investigation");
        bodyMap.put("content","bXkgbmV3IGZpbGUgY29udGVudHM=");
        return webClient
                .put()
                .uri("https://api.github.com/repos/chenrujun/repository_created_by_github-application-investigation/contents/hello.txt")
                .body(BodyInserters.fromValue(bodyMap))
                .attributes(oauth2AuthorizedClient(githubClient))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
