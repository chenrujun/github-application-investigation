# GitHub Application investigation

This repository is used to investigate GitHub application.

# Steps to Run this application

1. Read [docs about creating an OAuth App](https://docs.github.com/en/developers/apps/building-oauth-apps/creating-an-oauth-app), create an application with the following parameters:
   - **Application name**: application-2022-04-22
   - **Homepage URL**: http://localhost:8080
   - **Authorization callback URL**: http://localhost:8080/login/oauth2/code/github

2. Read [doc about modifying an OAuth App](https://docs.github.com/en/developers/apps/managing-oauth-apps/modifying-an-oauth-app), generate a new client secret. Copy Client ID and Client secret and paste to `src/main/resource/application.yml`.

3. Run SampleApplication.

4. Access `http://localhost:8080`, login by GitHub account.

5. Access `http://localhost:8080/github/user/orgs` to get organization list.

6. Access `http://localhost:8080/github/user/repos` to get repository list.

7. Access `http://localhost:8080/github/user/repos/create` to create a repository named `repository_created_by_github-application-investigation`.

8. Update the URL in `GitHubController#putUserRepos`, use your GitHub name to replace `chenrujun`. Then restart SampleApplication. Access `http://localhost:8080/github/user/repos/create` to add a new file `hello.txt` in the new created GitHub repo.
