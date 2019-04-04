# gs.calendar.appointments
An appointment system based on Google Calendars

[![Build Status](https://travis-ci.com/gmazzo/gs.calendar.appointments.svg?branch=master)](https://travis-ci.com/gmazzo/gs.calendar.appointments)
[![codecov](https://codecov.io/gh/gmazzo/gs.calendar.appointments/branch/master/graph/badge.svg)](https://codecov.io/gh/gmazzo/gs.calendar.appointments)

## Overview
This a sample project for demostrate the power of Kotlin language with a simple goal:
> Appointments scheduling on pre-given (event based) availaibility

I've used Kotlin everywere here:
- KTS for Gradle's scripts (bye bye Groovy)
- Kotlin's multuplatform for sharing DTO models between Java and JavaScript.
- Dagger for tide everying up
- RestEasy for API binding (JAX-RS spec)
- React, Redux and Material-UI, full Kotlin

## Modules
- **model**: platform agnostic model classes (for JVM and JS)
- **core**: main module to interact with Google Calendar API that provides an abstraction with business logic
- **backend**: a JAX-RS server which exposes the API and the frontend module (bundled as Java resources)
- **frontend**: a React application written in pure Kotlin! It relies on well known frameworks like Redux and Material-UI.

## Starting tips
### Configuring the project for your credentials
1. Setup the **admin** user
1.1. Locate the `build.gradle.kts` file in `backend` module
1.1. Change the `buildConfigField:ADMIN_USER_ID` entry with your GMail's email.
1. (optional) Replace the `google_client_secrets.json` with your own project on Google Cloud Platform. Make sure it has the **Calendar API enabled** if you do so.
1. Run the `backend` module (see below how to do it)
1. Visit http://localhost:8081/api/auth
1. Login with your account to allow the app to access your calendars. 

If succeed, you should be redirected to http://localhost:8081/api/agendas and see something like this:
```json
[
  {
    "id": "fsltp4vi7lcgugho31rdlc56no@group.calendar.google.com",
    "name": "Beneficios (prueba)",
    "description": "Calendario de prueba para la app de citas",
    "foregroundColor": "#000000",
    "backgroundColor": "#a47ae2",
    "available": true
  },
  {
    "id": "fpjlv6afhup6s03v5gt3ljgd74@group.calendar.google.com",
    "name": "Masajes (prueba)",
    "description": "Beneficio de masajes",
    "foregroundColor": "#000000",
    "backgroundColor": "#a47ae2",
    "available": true
  }
]
```
Note: the app, will not pick your personal calendar (the one linked to your account by default). 
You can change this behaviour by removing the fiter from the `AgendasServiceImpl` in `core` module:
```kotlin
.filter { it.primary != true }
```

I encourge you to create new calendars from https://calendar.google.com and add some test events on it!

### Running the backend
```sh
./gradlew backend:run
```
Once running:
- visit http://localhost:8081 to open the app 
- visit https://petstore.swagger.io/?url=http://localhost:8081/api/openapi.json to explore the API

Note: it may take a while to build, basically because it need to build and bundle the *frontend* module as well.

### Running the frontend (as standalone, for dev purposes)
```sh
./gradlew frontend:run -t -PapiEndpoint=http://localhost:8081/api
```
Once running, visit http://localhost:8088/ to open the app

## What to Expect?
<img width="852" src="https://user-images.githubusercontent.com/513566/55527104-fac2b880-566d-11e9-9885-2bff97c82757.png">
