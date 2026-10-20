# C4 diagram notes

## Context diagram
The context diagram deliberately omits internal code, data stores, and deployment details. The report system supports QA-1 by generating the monthly report, while its interaction with the email service supports QA-3 notifications when report status changes. Open client question: should the finance director receive an attachment or a secure link?

## Container diagram
The container diagram deliberately omits classes, internal algorithms, and server topology. The report batch supports QA-1 by processing monthly data, and the web report page supports QA-5 through server-side branch authorization. Open client question: which identity provider should authenticate branch managers?
