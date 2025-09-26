Assignment1 - Algorithms in Java

This project was created for the course "Design and Analysis of Algorithms (DAA)". It contains implementations of classical algorithms in Java.

Project Location

The project is located at:
C:\DAA-Daryn-Musseyev\Java hw\src\Assignment1

Requirements

Java 17 or higher

Maven 3.8 or higher

Git for version control

Build and Run

Clone the repository from GitHub.

Navigate to the project folder (Assignment1).

Build the project using Maven.

Run the tests with Maven as well.

Implemented Algorithms

1. MergeSort (D&C, Master Case 2)
• Linear merge;
• reusable buffer;
• small-n cut-off (e.g., insertion sort).
2. QuickSort (robust)
• Randomized pivot;
• recurse on the smaller partition, iterate over the larger one
• (bounded stack ≈ O(log n) typical).
3. Deterministic Select (Median-of-Medians, O(n))
• Group by 5, median of medians as pivot, in-place partition;
• recurse only into the needed side (and prefer recursing into the smaller side).
4. Closest Pair of Points (2D, O(n log n))
• Sort by x, recursive split, “strip” check by y order (classic 7–8 neighbor scan).

Metrics

operation counters

recursion depth tracking

CSV export of results

Testing

JUnit5 is used for testing. All algorithms have unit tests.

Continuous Integration

GitHub Actions is used for automated build and test checks.
Each push and pull request is automatically validated.

Benchmarks

Performance benchmarking is done with JMH (Java Microbenchmark Harness).
Comparison between selection and sorting algorithms is provided. Results are saved into CSV.

Git Workflow

The main branch contains only working versions.
Feature branches are created for each algorithm: feature/mergesort, feature/quicksort, feature/select, feature/closest, feature/metrics.

Main commits:

init: maven, junit5, ci, readme

feat(metrics): counters, depth tracker, CSV writer

feat(mergesort): baseline + reuse buffer + cutoff + tests

feat(quicksort): smaller-first recursion, randomized pivot + tests

refactor(util): partition, swap, shuffle, guards

feat(select): deterministic select (MoM5) + tests

feat(closest): divide-and-conquer implementation + tests

feat(cli): parse args, run algos, emit CSV

bench(jmh): harness for select vs sort

docs(report): master cases & AB intuition, initial plots

fix: edge cases (duplicates, tiny arrays)

release: v1.0

Releases:

v0.1 — first working version

v1.0 — final release
