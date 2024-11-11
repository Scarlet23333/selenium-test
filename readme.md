Before starting the test, please fill in the Username (NEU Email), EncryptPassword, and SecretKey fields in testdata.xlsx. To obtain the EncryptPassword and SecretKey, run GenerateEncryptPassword.java in ./src/main/java/com/seleniumtest. After entering your password, the program will generate the EncryptPassword and SecretKey for you.

Scenarios are extend from BaseTest. One step of one scenario is corresponding to one excel sheet. DataProviderFromExcel and @Test(dataProvider...) need to be modified when add new sheets.
