# Trackmodoro

## A *Day-in-the-life* Tracker

Some functionalities of **Trackmodoro**:
- Pomodoro study timer (50 minute-study and 10 minute-break) incorporated in the user interface
- Study to-do-list accompanied by a checkoff feature
- Able to log study "check-in (study)" time and "check-out (break)" time
- Sleep quality logger
- Exercise time logger
- End-of-day and end-of-week summary

Catered for those who want to incorporate a **productive** and **healthy** lifestyle, but still amiable to the masses, 
this software is engineered in the mindset of providing its users a helpful tool that will boost their mood and increase 
their productivity in all facets of life.

This project is of particular interest to me because I have been study timers and life tracker on the Internet, but 
none of them combines all the features I need; so I would like to put my personal spin to create a tracker that allows 
me to document multiple aspects of my life. The main objective is that I can make a software that I would be able to 
use personally to improve my life. The other reason is because this software is actually valuable; let's face it, who 
wouldn't want to get more things done than others and feel good about themselves? 

## User Stories 

- As a user, I want to be able to add a task, along with its desired time frame (how many 50-minute sessions), to my 
study-goal list. 
- As a user, I want to be able to remove a task.
- As a user, I want to be able to mark a task as completed, but not remove it.
- As a user, I want to be able to view all my non-completed tasks within my study-goal list.
- As a user, I want to be able to view all my completed tasks within my study-goal list.
- As a user, I want to be able to view all my tasks within my study-goal list, with each task's name, expected duration, 
and completion status. 
- As a user, I want to be able to set a timer for my tasks. 
- As a user, I want to be able to save my study-goal list to file.
- As a user, I want to be able to retrieve my study-goal list from the file. 

## Phase 4: Task 2

- Option 1: Test and design a class in your model package that is robust. The exception is thrown in the constructor 
for the Task class (public Task (String task, int num) throws NoTaskTimeException).  

- Option 2: Include a type hierarchy in your code. Tools class is the abstract superclass, and AddTool, RemoveTool, 
RetrieveTool, SaveTool, SetCompleteTool, and StartStopTool are its subclasses. 

## Phase 4: Task 3

- Change 1: To reduce coupling. So far, when I update my ListModel in the GUI I have to update my StudyGoal object 
together with it. For example, when I add a task to StudyGoals, I would need to add a new entry in my ListModel as well.
To fix this, I would create a new field in StudyGoals model class, which is of ListModel type, and have all the methods
inside StudyGoals to include the functionality of updating the ListModel. Then, I would be able to simply call addTask()
without having to update the ListModel simultaneously. 

- Change 2: At the moment, ClickHandler abstract class has a field of type StudyGoals, however, it resides inside 
Tool class and since Tool has an association with Panel and Panel has a field StudyGoals, I can refactor my code so that 
ClickHandler can get at the StudyGoals through the Panel field (which has StudyGoals) in Tool class. 
# Pomodoro_To_Do
