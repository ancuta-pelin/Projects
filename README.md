This repository contains some personal Java projects.

  Activitati_copil is an application which defines a class named Child. Define the methods and member variables for this class 
which enble a Child object to store/do the following:
- the name of the child
- the child’s birthday
- the child can introduce him/herself by „saying”: Hello my name is ...
- the child can tell his/her age
- the child can add two numbers smaller than 10 and return the result like so: The sum of X and Y is equal to Z
- the child knows how to say Goodbye!
- the child can speak the alphabet both in direct and inverse order
- the child can color a chess board given its dimensions by using alternative colors (for the colors use the 
symbols 1 and 0)
- the child can play dots and crosses (X-0) by him/herself.

  Program_cheie_de_autentificare is an application which defines an authentication key with the format: XXXXX-XXXXX-XXXXX-XXXXX,
where X is a character which can be either a digit or a letter. The application should verify if this key has exactly 
4 groups of characters with 5 characters each, and separated by the symbol ‘-‘. Also, compute the number of 
digits and letters from the authentication key. The number of digits should be greater than the number of 
letters, and the number of letters cannot be 0. 
If any of the above conditions are not met, display the message: “Invalid authentication key!”

  Joc_X_si_0_automat implements the naive dots and crosses game (X-O) as an automated game.

  Organizator_grup_persoane has an interface called Group which defines the following methods: adding, removing a person, checking if a 
person is in the group, listing all the persons present in the group at one point and listing in alphabetical order all the
distinct first name of the persons from the group. When a person leaves or is removed from the group, that person 
should say Goodbye to the rest of the people.

  Joc_de_Carti defines an array of String variables that will be populated with all the playing cards from a complete package. A 
series of randomly picked cards will be extracted until the current card will have the clubs sign and a value 
greater than 8. At each step, the current card and the number of already extracted cards will be displayed.

  Autentificare_utilizator considers a package of classes and interfaces called dbInteraction which enables the interaction with a database based 
on a user’s authentication. The package includes the following components:
- a class which defines objects of type Person 
- an interface for authentication with the methods createUser(), deleteUser() and login(). 
- an abstract class VerifyPerson which extends the Person class 
* the name and surname ca only contain alphabetic characters
* the length of the name and surname cannot be greater than 50 characters
* the e-mail address should be formatted as: [a-zA-Z._]@[a-zA-Z.].[a-zA-Z]{2-5}

  Verificare_numar_de_inmatriculare_masina an application which checks the Romanian vehicle registration numbers. Their format is the following: 
[L{L}][NN{N}][LLL], where L represents a letter, N a digit, and the curly braces represent the fact that for Bucharest the 
number is composed of a single letter in the first group, and the digit group can be composed of 3 digits. Implement a 
method which checks the registration numbers and throw exceptions (instances of specialized exception classes) specific 
to each error which may occur upon check-up (specialized messages). For example, if the county letters group is 
composed of 2 letters, the digit group cannot be of size 3. The last letters group cannot contain "I" and "O" on the first 
and last position.

  Manipulare fisiere A *.csv file is given that contains the following fields separated by the / symbol: name, first name, phone number, date
births, link to Facebook profile. To read the information from the file and generate new (individual) files what
contain only people with the following characteristics: people born in December, people whose numbers
by telephone are outside Romania or have a fixed telephone number, persons with the name Andrei or Nicolae and persons of
whose Facebook profile links have not been customized (they contain a random string of numbers at the end of it).

