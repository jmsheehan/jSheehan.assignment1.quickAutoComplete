# jSheehan.assignment1.quickAutoComplete
Altered version of brute force auto complete</br>

Operates the same as the brute force autocomplete with a couple of differences, therefore everything noted in the
bruteForce README.md is also relevant to this project.</br> 
<h3>Differences</h3>

1)  Sorting:</br>
    Converts the HashMap map to a TreeMap hence sorting it alphabetically by terms(keys).</br>

2)  Searching:</br>
    When searching for terms that match for the option chosen stop searching as soon as the last
    Entry which starts with or matches the input from the user. This can be done because the map has
    been sorted therefore all the entries that start with a String will be sequential, therefore as
    soon as an entry found after the first match does not start with the String input there will be
    no more matches.
