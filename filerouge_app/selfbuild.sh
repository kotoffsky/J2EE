#!/bin/bash

cd ../persons_lib
ant clear
ant clean
ant dist
cp dist/persons.jar ../filerouge_app/lib/
cd ../filerouge_app

cd ../services_lib
ant clear
ant clean
ant dist
cp dist/service.jar ../filerouge_app/lib/
cd ../filerouge_app

ant clear 
ant clean 
ant deploy
