#!/bin/bash
# Script used to deploy latest maps code to OresomeCraft Battles production server
echo "Starting OresomeBattlesMaps update!"
cd "`dirname "$0"`"

echo "Downloading latest code from GitHub..."
wget https://codeload.github.com/OresomeCraft/OresomeBattles-Maps/zip/master

mv master OresomeBattles-Maps-master.zip
unzip OresomeBattles-Maps-master.zip

cd OresomeBattles-Maps-master/
echo "Compiling..."
mvn clean install

echo "Replacing jar files..."
mv target/OresomeBattlesMaps.jar ~/servers/battles/plugins/OresomeBattlesMaps.jar
cd ..

echo "Cleaning up files..."
rm -rf OresomeBattles-Maps-master.zip
rm -rf OresomeBattles-Maps-master

echo "Completed! Changes will apply on next server restart!"
