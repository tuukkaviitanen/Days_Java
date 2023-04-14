# DAYS JAVA

Calendar event management console application
Queries and writes calendar events from and to a CSV file
Project uses Java with Maven.

## HOW TO USE

1. Make sure Java SDK (JDK) is installed on your machine
2. Create a directory called `.days` in your HOME DIRECTORY (ON WINDOWS C:/Users/{username}) (ON LINUX: /home/{username}) (usually)
3. Create `Events.csv` file inside `.days` and add "date", "category" and "description" headers(or don't if you want to add the events in-app). You can use the template `Events.csv` included in this repository for reference.
4. In Days_Java directory, run `./mvnw package` (or in WINDOWS CMD `mvnw.cmd`) to build `days.jar`. This is possible without having maven installed on your machine. You can also use `mvn package` if you have maven installed.
5. Program is now runnable in `target` directory by running `java -jar days.jar [params]`

## PARAMS

Same documentation is also found in program.
README UPDATED ON 2023-04-14. Most up-to-date params documentation can always be found in-app.
```
Usage: java -jar days.jar [command] [command options]
  Commands:
    list      List events to console
      Usage: list [options]
        Options:
          --after-date

          --before-date

          --categories

          --category

          --date

          --description

          --exclude
            Default: false
          --no-category
            Default: false
          --today
            Default: false

    delete      Delete event(s). Displays all deleted/would-be-deleted events
      Usage: delete [options]
        Options:
          --after-date

          --all
            Default: false
          --before-date

          --categories

          --category

          --date

          --description

          --dry-run
            Default: false
          --exclude
            Default: false
          --no-category
            Default: false
          --today
            Default: false

    add      Add event
      Usage: add [options]
        Options:
          --category

          --date
            Default: 2023-04-14
        * --description
```
## TESTING WITH SCRIPT

After building the app with maven, you should be able to run `test_script.py`.
It runs most of possible params of the app through console.
`Events.csv` included in this repository was used in testing.

HOW-TO:

In `Days_Java` directory run with:

On Windows `python test_script.py`
On Linux `python3 test_script.py`

## NuGet Packages

- com.beust.jcommander (1.82)
- com.opencsv.opencsv (5.7.1)

## Tools used

- IntelliJ IDEA Community Edition
- Visual Studio Code (for python testing script and README)
