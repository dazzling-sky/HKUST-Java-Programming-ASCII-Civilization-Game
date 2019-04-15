{\rtf1\ansi\ansicpg1252\cocoartf1671\cocoasubrtf200
{\fonttbl\f0\froman\fcharset0 Times-Bold;\f1\froman\fcharset0 Times-Roman;}
{\colortbl;\red255\green255\blue255;\red0\green0\blue0;}
{\*\expandedcolortbl;;\cssrgb\c0\c0\c0;}
\paperw11900\paperh16840\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\deftab720
\pard\pardeftab720\sl280\partightenfactor0

\f0\b\fs28 \cf2 \expnd0\expndtw0\kerning0
\ul \ulc2 \outl0\strokewidth0 \strokec2 Background 
\f1\b0\fs24 \cf2 \ulc2 \strokec2 \
\cf2 \ulnone \strokec2 \
Civilization is a turn based strategy game where the objective is to conquer other players' cities by developing our own resources and troops (reference). In this assignment, you will complete a partial implementation of a simplified, ASCII-version civilization game. This game involves two human players (according to the configuration in the players.txt file that comes with the skeleton). In each turn, players are able to perform tasks on cities through their ministers. Each human player has a set of cities under his/her governance. The human player will have a list of ministers to carry out the various tasks on the cities. Some of the possible actions are: collect tax, collect science and production points, build improvements (banks, roads, universities) and recruit troops, see the selectAndPerformAction() method in the GameEngine.java file for the list of possible actions. Each minister has different intelligence, experience and leadership values, these values are stored in the instance variables of the minister object. These values affect how much gold, science and production collected. A minister of the player can do one single task in each turn. If the player has 3 ministers, the player will be able to perform three tasks per turn. Gold, science points, and production points are the main resources of the game. The player can spend these resources to perform various tasks in the game, such as building banks, roads, universities, upgrading technologies, and recruiting troops. Using the recruited troops, players can attack adjacent cities to conquer them. The game will end when one player has conquered all cities on the map.}