# Java IV REST Controllers Lab

Completing this lab demonstrates your ability to code Spring REST
controllers in a standards based way.

## Objective

Write a fully functional REST controller for the customers API

## Prerequisites

* Create a "labs" directory if you don't already have one by opening a terminal
and typing:
``mkdir ~/labs``

## Getting Started:

1. [Fork](https://help.github.com/articles/fork-a-repo/) this repository (__don't__ "Clone or download" directly from my repository)
1. Open a terminal and change to your labs directory:
``cd ~/labs``
1. After the repo has been forked to your GitHub account, verify you are viewing your fork, then click on "Clone or download"
1. Click on the clipboard icon in the dropdown that appears to copy the URL
1. Go back to your terminal and type: ``git clone `` (with a trailing space)
1. In the terminal window, choose "Edit" -> "Paste" which should cause the repository URL to be appended to the ``git clone`` command
1. Press Enter

If all goes well you should see something like:
```
Cloning into 'java4-labs-rest-controllers'...
remote: Enumerating objects: 61, done.
remote: Counting objects: 100% (61/61), done.
remote: Compressing objects: 100% (24/24), done.
remote: Total 61 (delta 5), reused 61 (delta 5), pack-reused 0
Unpacking objects: 100% (61/61), done.
```

## Completing the Assignment

__Important__ You may not change the code in any test cases. _The only permissible difference between the base version of the JUnit test class and yours is yours will have no tests commented out and the file is otherwise identical._

1. Open [CustomerControllerTests](src/test/java/edu/cscc/java4/rest/CustomerControllerTests.java)
1. One by one, uncomment each test, changing just enough code or configuration to make the test pass
1. Once all tests are passing, commit your changes


## Submitting Your Work

Create a [pull request](https://help.github.com/articles/creating-a-pull-request/) to submit your work for grading:

1. Open a terminal
1. Change to the main directory for these labs: ``cd ~/labs/java4-labs-rest-controllers``
1. Push your changes to GitHub: ``git push origin master`` providing your GitHub login credentials if prompted to do so
1. Navigate to your copy of the repository in GitHub
1. Click "New Pull Request"
1. From the "Comparing changes" view, click "Create pull request"
1. Enter a proper tile and comments in the "Open a pull request" view
1. Click "Create pull request"
1. Verify your pull request is pending in the [main repository pulls](https://github.com/jeff-anderson-cscc/java4-labs-rest-controllers/pulls) list

__NOTE: I will provide feedback via. comments in your pull request.__
If you need to amend your work after you issue your initial pull request:

1. Commit your updates
1. Push your changes to gitHub
1. Verify the new commits were automatically added to your open pull request
