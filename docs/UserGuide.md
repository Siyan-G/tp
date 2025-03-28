---
layout: page
title: User Guide for A Caring Book (TP T12-2)
---

A Caring Book is a **desktop app for managing patient and staff contact details, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, A Caring Book can get your contact management tasks done faster than traditional GUI apps.

* Table of Content
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. Download the latest `.jar` file from [here](https://github.com/AY2425S2-CS2103T-T12-2/tp/releases/tag/v1.3).

1. Copy the file to the folder you want to use as the _home folder_ for your A Caring Book.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar acaringbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:


   * `liststaff` : Lists all staffs.

   * `listpatient` : Lists all patients.

   * `list` : Lists all contacts.

   * `addstaff r/doctor n/Mary Jane p/9929126 e/maryJ@example.com a/Spider street, block 333, #03-03` : Adds a doctor named `Mary Jane` to the Address Book.

   * `addpatient n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 dr/Dr Mak g/Mrs Doe dp/Conology` : Adds a patient named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addpatient n/NAME`, `NAME` is a parameter which can be used as `addpatient n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`



### Adding a patient: `addpatient`

Adds a patient to the address book.

Format: `addpatient n/NAME p/PHONE_NUMBER [e/EMAIL] [a/ADDRESS] [dr/DOCTOR_IN_CHARGE] [g/GUARDIAN] [dp/DEPARTMENT] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:

* `addpatient n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 dr/Dr Mak g/Mrs Hong Doe dp/Conology`
* `addpatient n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 dr/Dr Teo g/Mr Bui Crowe dp/Conology t/billed`

### Adding a staff: `addstaff`

Adds a staff to the address book.

Format: `addstaff [r/ROLE] n/NAME p/PHONE_NUMBER [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A staff can have any number of tags (including 0)
</div>

Examples:
* `addstaff r/doctor n/Mary Jane p/9929126 e/maryJ@example.com a/Spider street, block 333, #03-03`
* `addstaff r/nurse n/Mark Markerburg p/99137653 e/theMUCK@example.com a/Zaney street, block 666, #01-06` 

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Listing all patients : `listpatient`

Shows a list of all patients in the address book.

Format: `listpatient`

### Listing all staffs : `liststaff`

Shows a list of all staff in the address book.

Format: `liststaff`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [r/role] [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [do/DOCTOR_IN_CHARGE] [g/GUARDIAN] [dp/DEPARTMENT] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* Role field is only available for healthcare staff, doctor_in_charge, guardian and department fields are only avaliable for patients.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex charlotte` returns `Alex Yeoh`, `Charlotte Oliveiro`<br>
  ![result for 'find alex charlotte'](images/findAlexCharlotteResult.png)


### Finding a patient by department

Find patients whose departments match with the keyword.

Format: `findpatient KEYWORD`

* The search is case-insensitive. e.g `Conology` will match `conology`
* Only one keyword is allowed.
* Only the department of the patient is searched.
* Only full words will be matched e.g. `con` will not match `conology`.
* All patients in the department will be returned e.g. `findpatient conology` will return a list of Conology patients.

Examples:
If these two `addpatient` commands are executed,
* `addpatient n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 dr/Dr Mak g/Mrs Hong Doe dp/Conology`
* `addpatient n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 dr/Dr Teo g/Mr Bui Crowe dp/Conology t/billed`

then
* `findpatient conology` returns `John Doe` and `Betsy Crowe`.

### Finding a healthcare provider by role

Find healthcare staff whose roles matches with the keyword.

Format: `findstaff KEYWORD`

* The search is case-insensitive. e.g `Doctor` will match `doctor`
* Only one keyword is allowed. 
* Only the role of the healthcare provider is searched.
* Only full words will be matched e.g. `doc` will not match `doctor`.
* All healthcare staff matching the role will be returned e.g. `findstaff doctor` will return a list of all doctors.

Examples:
If these two `addstaff` commands are executed, 
* `addstaff r/doctor n/Mary Jane p/9929126 e/maryJ@example.com a/Spider street, block 333, #03-03`
* `addstaff r/nurse n/Mark Markerburg p/99137653 e/theMUCK@example.com a/Zaney street, block 666, #01-06`

then 
* `findstaff doctor` returns `Mary Jane`
* `findstaff nurse` returns `Mark Markerburg`.


### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Patient** | `addpatient n/NAME p/PHONE_NUMBER [e/EMAIL] [a/ADDRESS] [do/DOCTOR_IN_CHARGE] [g/GUARDIAN dp/DEPARTMENT] [t/TAG]…​` <br> e.g., `addpatient n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 dr/Dr Mak g/Mrs Ho dp/Conology t/friend t/colleague`
**Add Staff** | `addstaff [r/ROLE] n/NAME p/PHONE_NUMBER [e/EMAIL] [a/ADDRESS] [t/TAG]…​` <br> e.g., `addstaff r/doctor n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [r/ROLE] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [do/DOCTOR_IN_CHARGE] [g/GUARDIAN] [dp/DEPARTMENT] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Find Patient By Department** | `findpatient KEYWORD`<br> e.g., `findpatient surgery`
**Find Staff By Role** | `findstaff KEYWORD`<br> e.g., `findstaff nurse`
**List** | `list`
**List Patients** | `listpatient`
**List Staff** | `liststaff`
**Help** | `help`
