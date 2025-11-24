#Campus-Course----Records-Manager--CCRM-


So, um, CCRM stands for Campus Course & Records Manager. It’s basically a small console program written in Java that helps keep track of student-related stuff — like courses, grades, transcripts, all that academic paperwork colleges deal with.

The idea was to make one place where you can manage everything in a neat way. Think of it like a little hub, but instead of clicking around in a big app, you just type commands in the console.

Honestly, this project was kind of an experiment for us. We wanted to practice a lot of the modern Java things we’d learned — OOP, exceptions, file handling with NIO.2, and even streams. It’s like we tried to pack in as much “real Java” as we could.

Getting it running

It’s not too complicated. You just need Java installed — JDK 1.8 or anything newer works fine. After you clone the repo, you can just compile and run the Main class.

There’s a file called USAGE.md that shows some example commands and sample data if you don’t want to figure it all out on your own. Also, we dropped a few screenshots in the screenshots/ folder so you can see what the menus look like before running it.

Quick chat about Java itself

Java has been around since the mid-90s. It started small but got popular really fast. Over the years, big updates kept adding useful features. Like in 2004, Java 5 brought generics and enums. Then in 2014, Java 8 introduced lambdas and streams — which honestly changed how people write Java code.

Java is kind of like a family:

Java ME is the tiny version, made for early phones and embedded devices.

Java SE is the standard one — good for normal desktop apps or simple servers.

Java EE is the enterprise edition, used for huge business applications with lots of moving parts.

And then there are the three building blocks:

The JDK (Java Development Kit) is like the full toolbox.

The JRE (Java Runtime Environment) is just enough to run programs, not build them.

The JVM (Java Virtual Machine) is the engine that actually makes everything run.

Setting things up

On Windows, you just download the JDK from Oracle, run the installer, and then set your environment variables (JAVA_HOME, plus updating the Path). If you open Command Prompt and type java -version, you’ll know it’s working.

If you prefer an IDE, Eclipse works fine. Install it, make a new Java Project (maybe call it CCRM), select the JDK you installed, and you’re good to go. Again, there are screenshots of this too.

What’s inside CCRM

Here are some of the features we built in:

Student & Course Management: Add/update students and courses with proper fields (like registration numbers, credits, etc.).

OOP: We used all the classic concepts — encapsulation (private fields with getters/setters), inheritance (a Person base class extended by Student and Instructor), and polymorphism (treating different objects as Person when needed).

Modern APIs: Streams for filtering/searching, and the Date/Time API for tracking enrollments.

File Operations: Import/export data from text files, plus a backup function that makes timestamped folders using NIO.2.

Design Patterns: Singleton for config, Builder for creating Course objects more cleanly.

Custom Exceptions: For example, if someone tries to enroll twice or exceed credit limits, the app throws its own exceptions.

So yeah, CCRM isn’t a giant system, but it’s a nice mix of concepts and a good practice ground for Java.
