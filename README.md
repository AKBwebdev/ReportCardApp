# Report Card App
It uses a custom Java class to generate the report of a school student.
It takes Student Name, Teacher Name, Student Year, Subjects, Marks, and a Special Message as inputs to prepare the report.

The class also validates and sanitizes data where appropriate.
Validations performed by the class are as follows:
<ul>
     <li>Checks if an input name contain characters other than a-z, A-Z, space and '.
             If it does, then removes those characters, and converts name to Title Case.</li>
     <li>Checks if Student Year is between 1 and 13; if not it sets year to 1.</li>
     <li>Checks if a Subject contains characters other than a-z, A-Z and space.
             If it does, then removes those characters.</li>
     <li>Checks if any input marks are less than 0 or greater than 100, in which case
             it sets marks to 0.</li>
</ul><br />

![reportcard](https://cloud.githubusercontent.com/assets/22053146/26172619/f834ecfe-3b40-11e7-88e3-a53b584ce612.png)
