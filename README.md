# Portfolio Project
Over the next ~6 months I want to spend a bit of time per day contributing to a project that is more deeply thought out and of larger scope than the school projects I have done. This repository is public and as I get going I am likely to need some help, particularly with the front end and ML aspects. My plan is for this to end up being an investment simulator, in which users are given fake money, which the user can invest how they see fit and track their progress over time based on the actual markets. I also want to use ML to give stock tips, which is a pretty intensive feat so I would be happy if statistically this engine does any better than a complete guess.
## Tech Stack
For the front end, I will be using React.js. This frontend server will depend on two "microservices", although one of the services will be fairly large. A Spring Service will provide the majority of core application functionality like login and registration as well as all of the functionality associated with the simulator side of the application. Because, as I understand it, python is the most powerful tool for machine learning and because endpoints interacting with the predictive aspects of this application are going to take large amounts of time, I am going to use flask to build a separate microservice interacting with these endpoints. 
#### API
For the API calls that will give me the live stock data needed by this site I will use the Data API provided by https://darqube.com/. This site only allows for a limited number of calls per day, but 1000 calls per day should definitely get the job done for a while unless I need to start upscaling this in the future. Either way the API will only be accessed via a wrapper that I create, so design is modular enough that only the wrapper will need to be changed in order to handle a new API.
##  Project Management
### Contact
If you are interested in taking part in this project, please email me at omw3@pitt.edu.
### Trello
To see open user stories, sprint information and long term goals check out my [Trello Board](https://trello.com/b/uBXwmjyn/investmentproject-trello) to see progress and immediate plans.
### Design
To better understand implementation plans as well as completed implementation, please checkout my [project diagrams](https://github.com/OWurst/PortfolioProject/tree/main/Diagrams#readme).
## Difficulties
I am working as a software engineering intern this summer and will be a full time student in the fall, so it has been quite difficult to find time for this project and it will continue to be quite difficult. Hopefully I can carve out a little time each day, but it is going to be hard to build this project in 30min-1hr increments, even if I am working on it nearly every day. Also, I have virtually no experience developing ML, and both flask and spring have only been used to create simple school projects, so I am going to have to work pretty hard to keep my head above water on this project and avoid smashing my computer into a million tiny pieces.
