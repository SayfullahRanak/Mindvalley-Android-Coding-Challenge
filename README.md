# Mindvalley-Android-Coding-Challenge

# Assignment - Android Coding Challenge (MindValley)

To develop a  simple app that shows list of channels categories and new episodes. [API] 1. new episode : https://pastebin.com/raw/z5AExTtw
2. channels : https://pastebin.com/raw/Xt12uVhM
3. Categories : https://pastebin.com/raw/A0CgArX3



## Programming Language, Design Pattern

### KOTLIN,MVVM

## Dependencies 

### Hilt, Coroutine, Room , mockito, Junit, Espresso 

- Hilt is been used for providing di support throughout the Application

- Coroutine is been used to communicating between modules

- Room database is been used for caching data in local for offline support.

## Primary Overview
- Application consist of 1 activity "ChannelListActivity" where ChannelListActivity responsible for showing list of new episodes, channel list and category list from api.

- To implement Support offline-first , Room data base is been used where everytime after data loaded from api save, data is been saved to local database. Before saving the application clears the last chached data, as a result only the latest data is saved in local


# Package Contains (Main)

# entities 
- models for api and database

# db 
- database and query classes

# repo 
- main Repo

# RestService 
- provides api end point

## DI

::::::: Application Module, Context Module :::::::

- ApplicationModule provides dependencies which required throughout 

-  ContextModule provides context

# UI 

::::: ChannelListActivity, Splash :::::

## ChannelListActivity
::::: Adapter, view, viewmodel :::::

##List
- CategoryList (Gridview)
- ChannelList (vertical)
To differentiate between a Series and a Course, take a look at the
Channels object in the JSON provided and follow the pseudocode below
If​ the ​Series​ object inside ​Channels​ exists show the ​Series​ design
else
show the ​Course​ ​Design​.
--EpisodeList (Horizontal)
--SeriesList (Horizontal)


# Adapter
- contains adapter for recyclelistview and viewholder to contain the view of each entities of the listview 

- adapter -> adapter for recycleview, viewholder for grid, network viewholder

# view 
- channelList to show the list in recyclelistview

- viewmodel -> viewmodel for api call and other data logic

# Utils

:::: cons,MyApplication,ProjectUtils:::::

- Cons -> Constant variables needed throughout the app

- MyApplication -> Applicatin Class, also providing appInstance

- ProjectUtils -> supportive tools for projects, ex : .showAlert, screen width, px

- NetworkUtils -> used for network inspection

- Decorators -> used for spacing between entities in list

- UnsafeOkHttp -> okHttpSupport for network bellow android 19



# Package Contains (Test)

::::: testUtils, ui, utils ::::::

#testUtils
::::: LiveDataUtil, MockInterceptor, RestServiceTest, TestCons:::::::

- LiveDataUtil ->getOrAwaitValue observe live data from test class
- MockInterceptor -> MockInterceptor to mock the api call
- RestServiceTest -> Mock rest service and retrofit builder
- TestCons -> dummy contents.

#UI

- ChannelRemoteViewModelTest
##Test cases

--- Applied in all three lists (channelList, categoryList, newEpisode list)--
-_populatinglist_when_api_response_success
- _empty_when_api_response_empty
- _Error_when_response_code_not_200
- database_contains_old_list_when_no_internet
- _networks_when_no_network
...............................................

- ChannelListTest
#TestCase
- activityVisible

#Utils
- ProjectUtilsTest

