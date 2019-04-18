[![Build Status](https://travis-ci.com/EduardVavrynchuk/HotelBooking.svg?branch=master)](https://travis-ci.com/EduardVavrynchuk/HotelBooking)

# Hotel booking

## Reference Documentation
For further reference, please consider the following sections:

* [Project documentation](https://hotelbooking2.docs.apiary.io/#)

## What tools did I use?

The following tools I did used:

    Java 8 (1.8.0_192)
    Maven (3.6.0)
    H2 (1.4.199)
    SpringBoot (2.1.4.RELEASE)
    Hibernate (5.3.9.Final)
    
    PostgreSQL (42.2.2)

## How to run app?

You should have Java and Maven. After installation, you need to clone this repository:

    git@github.com:EduardVavrynchuk/HotelBooking.git
    
## And run the program:

    mvn spring-boot:run
    
## How to run test?

    mvn test

## Also you can run project with PostgreSQL DB:

You should have PostgreSQL, After installation you need create DB and user, also grant user access on DB, commands

    1. sudo -u postgres createdb hotel_db;
    2. sudo -u postgres createuser hotel_user;
    3. sudo -u postgres psql hotel_db;
    4. ALTER USER "hotel_user" WITH PASSWORD 'qwAS123zx';
    5. GRANT ALL PRIVILEGES ON DATABASE hotel_db TO hotel_user;

After that you need to uncomment Postgres Configuration in src/main/resources/application.properties and comment H2 Configuration