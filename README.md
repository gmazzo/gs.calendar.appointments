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
- **backend**: a JAX-RS server which exposes the API
- **frontend**: a React application written in pure Kotlin! It relies on well known frameworks like Redux and Material-UI.
- **webapp-bundle**: the backend module, bundled with the frontend module as Java resources. This should be your entry point if you want to run the app as a standalone whole

## Starting tips
### Setup the Google Cloud Project
1. Go to [Google Cloud Platform Console](https://console.cloud.google.com/apis)
1. Sign in and create an API Project
1. Enable the [Google Calendar API](https://console.cloud.google.com/apis/api/calendar-json.googleapis.com)
1. Create a Service Account (for the backend)
    1. Go to [Service Accounts](https://console.cloud.google.com/iam-admin/serviceaccounts)
    1. Choose *Create Service Account*
    1. Enter an ID and confirm
    1. Take note of your **Service Account's email address**
    1. Create a key-pair credentials for your new service account, in JSON format
    1. Download the credentials and replace the `google_client_secrets.json` in `backend` module's resources (under `src/main/resources`).
1. Create an OAuth Client ID
    1. Go to [Credentials](https://console.cloud.google.com/apis/credentials)
    1. Choose *Create Credentials* and pick *OAuth Client ID*
    1. Pick *Web Application*
    1. In *allowed origins* section add this for dev localhost server:
        - `http://localhost:8081` for backend dev server
        - `http://localhost:8088` for frontend dev server (optional)
        - *(your production servers here)*
    1. Confirm it. You can let *allowed redirect URIs* empty. We will be using Google's own sign in.
    1. Take note of your **OAuth Client ID**
    1. Replace the `buildConfigField:API_CLIENT_ID` entry on `frontend` module's `build.gradle.kts` file.
1. Create a new (Google Calendar)[https://calendar.google.com/calendar/r/settings/createcalendar]
    1. Share it with the **Service Account's email address** you just created. This will make it available at the app
    1. Make sure it has **write access** to the Calendar's events when you share it
1. Run the `backend` or the `webapp-bundle` module to check if everything is working as expected.

If succeed, you should see something like this at http://localhost:8081/api/agendas:
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

I encourge you to create new calendars from https://calendar.google.com and add some test events on it!

### Running the backend
```sh
./gradlew backend:run
```
Once running:
- visit https://petstore.swagger.io/?url=http://localhost:8081/api/openapi.json to explore the API

### Running the frontend (as standalone, for dev purposes)
```sh
./gradlew frontend:run -t -PapiEndpoint=http://localhost:8081/api
```
Once running, visit http://localhost:8088/ to open the app

### Running the whole webapp (frontend and backend)
```sh
./gradlew webapp-bundle:run
```
Once running:
- visit http://localhost:8081 to open the app 
- visit https://petstore.swagger.io/?url=http://localhost:8081/api/openapi.json to explore the API

Note: it may take a while to build, basically because it need to build and bundle the *frontend* module as well.

## What to Expect?
<img width="852" src="https://user-images.githubusercontent.com/513566/55527104-fac2b880-566d-11e9-9885-2bff97c82757.png">
