Please fill Username(NEU E-mail), EncryptPassword, and SecretKey in the testdata.xlsx before starting test.
The EncryptPassword and SecretKey can be generated by GenerateEncryptPassword.java, which is located in ./src/main/java/com/seleniumtest.

Scenarios are extend from BaseTest.
One step of one scenario is corresponding to one excel sheet.
DataProviderFromExcel and @Test(dataProvider...) need to be modified when add new sheets.