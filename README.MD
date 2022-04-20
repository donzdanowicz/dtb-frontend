GitHub Repository with Backend: https://github.com/donzdanowicz/dtb-backend

Instructions of use:
1. Create database user in MySQL according to application.properties
2. Run backend application with DareToBudgetApplication class.
3. Run frontend application from above given link with DareToBudgetFrontApplication.
4. Type in http://localhost:8083/ in your browser.
5. Add user
6. Don't delete it! (below you'll find out why)
7. Enjoy

VERY IMPORTANT!!!

For valid operation of application first thing you have to do is add User.
Otherwise it won't work properly.
Then you can continue with checking frontend application.

Because frontend is still developed there is a big problem I didn't yet fixed:
When you create a user and it gets id 1 then everything works perfectly.
Problem is when it has a different id (for example when first made user
was deleted). Then, for application to work properly, you have to delete
all tables in MySQL Workbench to reset everything.

regarding any problems please contact me:
donzdanowicz@o2.pl

About project:
Design patterns
- Builder (Entry, EntryDto, NetWorth, NetWorthDto)
- Singleton (DbManager, Services in frontend)
- Facade (Currency, Entry, NetWorth, User)

Scheduler
- checking popular currencies' rates every day at 10 and 15.