# Lab-01: architecture foundations of the sales report system

Team: team 7 · Members: Pong Mengheang (menhean0168-ctrl), Chhi Layhorng (chhilayhorng)

## Files produced in this lab
| File | Purpose | Author(s) |
|------|---------|-----------|
| lab01/quality-scenarios.md | QA-1..QA-6, utility tree, drivers | Layhorng |
| lab01/c4-context.mmd, .png | C4 level 1 | Layhorng|
| lab01/c4-container.mmd, .png | C4 level 2, release 1 | Layhorng |
| lab01/c4-notes.md | notes on the C4 diagrams | Layhorng |
| lab01/design-review.md | cohesion and coupling review | Heang |
| lab01/adr/0001-architecture-style-release-1.md | ADR-0001 | Heang |
| lab01/adr/0002-branch-to-head-office-data-flow.md | ADR-0002 | Heang |
| pom.xml, src/**, tools/SampleData.java | sales-report 0.1.0-SNAPSHOT | Heang |

## Inputs reused from earlier labs
None (first lab).

## How to run
    java tools/SampleData.java      # generate sample data
    mvn -q verify                   # build and test
    mvn -q compile exec:java        # load data, print progress

## Change log
| Date | Lab | Change | Commit or tag |
|------|-----|--------|---------------|
| 2026-10-19 | Lab-01 | Project created: model, parser, renderers, loader; 2 ADRs | lab-01 |
