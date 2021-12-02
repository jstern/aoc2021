# aoc2021

Solutions to [advent of code 2021](https://adventofcode.com/2021/).

![build status](https://github.com/jstern/aoc-2021/actions/workflows/build/badge.svg)

Assumptions:

* Make and JDK 17 are available
* A valid session cookie value is stored in `.aoc-session` in the project root

Doing stuff:

* `make input [year=Y] day=D` [fetches, saves and] prints the puzzle input for the specified day
* `make start day=D` generates and commits initial boilerplate for the specified day
* `make test` runs the tests
* `make [run] day=D part=P` prints the solution for the specified day and part
