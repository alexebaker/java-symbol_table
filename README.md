# Expression Trees

CS 554 Spike 3 - Symbol Tables


## Contact Information

    Author: Alexander Baker
    Email: alexebaker@unm.edu
    Date: 03/09/2018


## Usage

Build the project:

```bash
make jar
```

Run the project:

```bash
java -jar spike3.jar tests/t11.blki
```

Run the test cases:

```bash
make test
```


## Specification Issues

s3.5.3 Error Handling

    In addition to handling the errors specified in this section, an aditional error is thrown if an IDENTIFIER is declared twice in DEFS. For example:

    signed a;
    unsigned a;

    Will result in an error being thrown. The second declaration has no effect, and this first declaration is used. In this case, the resule will be a
    variable a of type signed.

## Known Bugs


No known bugs at this time.
