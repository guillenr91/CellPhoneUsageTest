## Design Notes

- Coded in layers (repository, service, model) to keep the logic separated and make it easier to test
- There is a "service" to generate each section of the report, this is mainly to keep logic separated so that each
  sections can also be reusable for other reports, if needed.
- Using interfaces to define repositories, so that if we need to change the source of the data (repositories
  implementation), the services using those repositories will require no code changes.

## Assumptions

- The data is being read directly from the provided CSV files, but code was designed to make it easy to make the changes
  to read data from a DB instead
- Given the data available on the `CellPhone.csv`, I could not exactly say what's the format for the `purchaseDate`
  column, so I'm assuming it's `yyyyMMdd` since it works with the provided set of data
- Each employee only has 1 phone number (1:1)
- The `totalMinutes` & `totalData` are all in the same unit of measure, therefore no conversions are needed
- If more than one entry is found for a given `employeeId` in a given month, their `totalMinutes` & `totalData` are
  going to be added. This could be used to fix scenarios where the data for a given month was not correct. So we could
  add another entry for the same month to correct that
- The report will contain data for ALL years the phone has been used since no year was specified

## TODOs

- Still missing the code that would print the file to a local printer, but for this piece of code I would ask if we
  really need to print the file or if generating a file (CSV or PDF) is fine. This file could be later be sent in an
  email or saved to some cloud storage service
- Code documentation, unit & integrations tests not being added because of time constraints, but that would be something
  I would like to add as well
- There is also almost no logs, so it would be hard to follow application's execution or get more information about
  errors.
- There is also room to improve the exception handling, the application will throw all exceptions but we can change that
  depending on the behaviours required for the application