# CPSC-210 Polynomial Calculator

## by Caeleb Koharjo

**Proposal**:
- What will the application do?

My application will work as a calculator in the form of a desktop application. 
It will be able to find derivative of given polynomials,
and do simple calculations.
- Who will use it?

This application is aimed towards math students and teachers, but can be used
by people from other fields that require a calculator that can handle polynomial derivatives
and simple calculations.
- Why is this project of interest to you?

This project is of interest to me because it combines the two disciplines
that I enjoy learning, math and computer science. It allows me to apply both my knowledge in mathemetics, but also
allows me to further develop my knowledge on how math is represented on computers. Moreover,
it gives me a stronger idea on how my calculator presents and manipulates the information that is recieved.

---
##User Stories:

- As a user, I would like to be able to add and subtract and multiply and divide on my simple calculator
- As a user, I would like to be able to create polynomials 
- As a user, I would like to be able to add a simple mathetmatical
statement to my simple calculator.     
- As a user, I would like to be able to save the inputted statements in my simple calculator.
- As a user, I would like to be able to load the saved inputted statements in my simple calculator.
- As a user, I would like to have the loaded statement evaluated on my calculator in my simple calculator
- As a user, I would like to add polynomials to my derivative calculator
- As a user, I would like to find the derivative of my polynomial
- As a user, I would like to save my polynomial inputted
- As a user, I would like to load my saved polynomial
- As a user, I would like to evaluate my polynomial at a point

##Instructions for Grader:
- You can trigger my audio component by

pressing one of two buttons at the beginning of the program.

- You can generate the first required event by

Running the main class and selecting the Simple Calculator button (which will play an audio sound when pressed)
this will open the simple calculator.

- You can generate the second required event by

Adding a mathematical expression into the calculator's input field. 
However, it must have a space between operations and operands,
and at its current state, it does not have order of operations. And by
clicking evaluate, you can see that the mathematical expression that is
added to the calculator will be evaluated (ignoring order of operations).

- You can save the mathematical expression inputted by

Clicking the save button at the bottom will save whatever you have
in the input field into a text file.

- You can load the mathematical expression inputted by

Clicking the load button at the bottom will load the saved expression
and put it into the calculator and automatically evaluate it. To test this,
save an expression put into the input field, change the input field and eval, 
and then load, and you'll see the saved expression inputted and evaluated.


---
##Phase 4


###Task 2
- Make appropriate use of the Map interface somewhere in your code. 

I used a map to represent polynomials, using the keys as degrees of the term and 
values as the coefficient of the term.
I asked if this was okay here (and it has a better explanation):
https://piazza.com/class/k4erikupq2efg?cid=1585

- "the class where the Map interface was used"

The usage can be found in the polynomial calculator class.


###Task 3

Example of Cohesion:

I originally used an interface for the Calculator class, 
where it had the methods (without the implementation, just the header).
Also, I did this during phase 1, so I had weak understanding of interfaces:

![image1](https://i.imgur.com/QMpWmSz.png)

However I noticed that both my Polynomial & Simple calculators that
implemented the calculator interface had the same implementation of these
add, subtract, multiply and divide methods. I also noticed that
both simple & polynomial calculator classes
had a valid expression checker method that was implemented
differently due to both calculators searching for different
kinds of expressions. 

SimpleCalculator valid()
![image2](https://i.imgur.com/w50hFVC.png)
PolynomialCalculator valid()
![image3](https://i.imgur.com/ff19Lhs.png)

Therefore, I changed my calculator interface into a abstract class 
that had implementations for the add, subtract, divide, 
multiply methods, but had an abstract method called valid whose 
purpose was to check for validity of the expression inputted
and made it abstract since every type of calculator would have
a different implementation to check if the expression is valid.
This increased cohesion due to the fact that add, subtract, multiply and divide
was originally in each individual calculator as duplicate code, but now
the calculator abstract class contains the code for those similar methods. This
makes it so that the SimpleCalculator class and PolynomialCalculator class can focus
on their specific types of calculations rather than handling basic fundamentals
of calculators (since both extend Calculator abstract).

![image4](https://i.imgur.com/2EBDLzg.png)

While writing this out, I also noticed that both subtype (simple & polynomial) calculators share
duplicate isNumber methods, so I also made that part of the Calculator abstract
class as that method is crucial for verifying calculator expressions
no matter the subtype of calculator. Therefore, increasing
cohesion by keeping the fundamentals of a calculator in the abstract calculator class, which
 in turn lets the more specific types of calculators have code that
  focus on specific calculations.

The addition of isNumber to the abstract Calculator class:
![image5](https://i.imgur.com/xrdcVA1.png)


Another example of cohesion:

In my panels package, I made it so that the classes with Frame in the name
focus on building the actual window of the application, whereas,
the classes with panel in the name would focus on putting the components
onto the window and control all the inputs and outputs on the screen.
This allows each class to focus more closely on their specific purpose,
as instead of making the jpanel in the Frame class, I made it seperate
so that the Frame classes focus on making the frame and adds the
Panel classes that focus on making the panels inside the frame.

Example of SimpleFrame focusing on building the frame:
![image6](https://i.imgur.com/DhGKhP7.png)

Example of SimplePanel focusing on building the components
within the frame:
![image7](https://i.imgur.com/kGj0zIw.png)
