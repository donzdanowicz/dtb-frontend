GitHub Repository with Backend: https://github.com/donzdanowicz/dtb-backend

Instructions of use:
1. Create database user in MySQL according to application.properties
2. Run backend application with DareToBudgetApplication class.
3. Run frontend application from above given link with DareToBudgetFrontApplication.
4. Type in http://localhost:8083/ in your browser.
5. Add user
7. Enjoy

regarding any problems please contact me:
donzdanowicz@o2.pl

About project:
Design patterns
- Builder (Entry, EntryDto, NetWorth, NetWorthDto)
- Singleton (DbManager, Services in frontend)
- Facade (Currency, Entry, NetWorth, User)

Scheduler
- checking popular currencies' rates every day at 10 and 15.