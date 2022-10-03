
# Description
Source code for project 1 :
"Project 1 – Automaton. Cloning egrep command supporting simplified
ERE.s".

## Contributors

* Quan Nguyen
* Quang-Toan Tran

# Example

# How to use

## Use IntelliJ (IDE)
You can use Intellij IDEA with Ant Build to create archive with `jar` et run the program.
* Fill the champs "arguments of application" with `<Regex> <file-path>` :
    * `<Regex>` is an regular expression with the form of ERE
    * `<file-path>` is the path of the file


## Compiler and executing with Terminal - CLI
* Install Ant and JDK with version >= 1.8
* Use `ant dist` to create archive in `jar` in the folder `build`
* `cd build`
* `java -jar egrep.jar <Regex> <file-path>`.

