#!/bin/bash
# Script used to deploy latest maps code to OresomeCraft Battles production servers
echo "Starting MapsPlugin update!"
cd "`dirname "$0"`"

echo "Downloading latest code from GitHub..."
wget https://codeload.github.com/OresomeCraft/MapsPlugin/zip/master

mv master MapsPlugin-master.zip
unzip MapsPlugin-master.zip

cd MapsPlugin-master/
echo "Compiling..."
mvn clean install

echo "Adding files to update directory..."
cp target/MapsPlugin.jar ~/servers/battles/plugins/Updater/MapsPlugin.jar
cp target/MapsPlugin.jar ~/servers/arcade/plugins/Updater/MapsPlugin.jar
cp target/MapsPlugin.jar ~/servers/tiot/plugins/Updater/MapsPlugin.jar
cd ..

echo "Cleaning up files..."
rm -rf MapsPlugin-master.zip
rm -rf MapsPlugin-master

echo "Completed! Changes will apply on next server restart!"
