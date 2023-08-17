  # Doctor's Office Application

The Doctor's Office Application is a Java desktop application that facilitates prescription management and provides patients with access to their prescriptions. It offers a secure and efficient way for doctors and patients to communicate and manage medical information.

## Features

- User Authentication: Secure login system for doctors and patients to access their respective accounts.
- Doctor's Dashboard:
    - Prescription Management: Create, manage, and update prescriptions with medication details, dosage, frequency, and instructions.
    - Patient Management: Add and manage patient profiles, including personal information, medical history, and contact details.
- Patient's Dashboard App:
    - Secure Access: Patients can securely access their prescriptions on their app.
    - Prescription Access: View prescriptions, including medication name, dosage, frequency, and instructions.
    - Password Change: Patient can also change their password to their liking.

## User Stories

1. As a doctor, I want to be able to add new patients with their medical history
2. As a doctor, I want to be able to create and manage prescriptions for my patients easily.
3. As a doctor, I want to have access to my patients' medical history for accurate diagnosis.
4. As a doctor, I want to have an option to save any changes that I had made to my patients.
5. As a doctor, I also want to be able to restore my data back to before I made any changes.
6. As a patient, I want to access my prescriptions whenever and wherever I need them.
7. As a patient, I want to be able to change my login password.

## Instructions for Grader
  The admin login for the application is **admin** and password is **12345**. 
  And the default password for a patient is **password**. 

  You can generate the required actions by following these steps - 
- To add a new patient :
  - On the startup screen, login with the admin login. 
  - There will be a button called Add Patient, click that and fill the details about the patient.
  - As soon as you click Add Patient! , you will be prompted to login. A new patient has successfully been created.
- To delete the patient :
  - On the startup screen, login with the admin login.
  - There is a button called search patient, search the name of the patient that you wish to delete.
  - Once searched, you can see all the details and the actions that can be done to the patient.
  - Choose the delete patient button and you will be prompted a confirmation screen.
  - Clicking Yes on the confirmation screen would delete the patient and prompts you to login. Clicking No would reset back any changes done and get you to the login page.
- To edit the patient :
  - On the startup screen, login with the admin login.
  - There is a button called search patient, search the name of the patient that you wish to delete.
  - Once searched, you can see all the details and the actions that can be done to the patient.
  - There you can select to either edit the patients details or their prescription.
- To view all the patients added :
  - On the startup screen, login with the admin login.
  - You will be prompted a few options in terms of buttons. 
  - Select the button that says display all patients and you would be able to see a list of patients added.

You will be able to find the visual component of my project as soon as you start the application on the login page.

To save/recover the data, do the following steps -

- To save :
  - On the admin login or the patient login once you have made the changes, you will be prompted back to the options screen where you will see an option to save changes.
  - On clicking that button you will be prompted to choose Yes to save all the changes made or choose No to fetch back all the data from files.
- To recover :
  - To recover, you can also press the Reload all patients button after the doctor login.
  - Pressing this button would read all the data from file once again and prompt you with a login page.

## Phase 4: Task 3

After creating the UML design diagram for the project I realised that there were too many dependencies to the model classes.
If I had the chance to refactor my code I would make a class for the ListOfPatients and then make a subclass of Patient in that.
Similarly for Medication and ListOfMedication, One class with subclasses would be better. It would refine my code majorly
and make it easier to debug if I do face any problems going forward with it.

Also I could have implemented some Error Handling in my project so as to curb any thing to which my program might crash.
It is so that the user can not put any inputs that the program is not expecting which might lead to unexpected behaviour.
