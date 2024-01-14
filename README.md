# Cron Parser

This Java application parses a cron string and expands it into a human-readable schedule.

## Table of Contents

- [Introduction](#introduction)
- [Usage](#usage)
- [Output](#output)
- [License](#license)

## Introduction

The Cron Parser is a Java application that takes a cron string as input and expands it into a human-readable schedule. The expanded schedule includes information about minutes, hours, days of the month, months, days of the week, and the associated command.

## Usage

To run the Cron Parser, provide a cron string as a command-line argument:

```sh
java CronParserApp '*/15 0 1,15 * 1-5 /usr/bin/find'
```
The application will display the expanded schedule in the following format:

```commandline
minute      0 15 30 45
hour        0
day of month 1 15
month       1 2 3 4 5 6 7 8 9 10 11 12
day of week  1 2 3 4 5
command     /usr/bin/find

```

