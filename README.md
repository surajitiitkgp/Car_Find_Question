# Car_Find_Question

#### A dataset of Cars is provided in a file (cars_input1.txt). The file has the three fields for each car: Name, Origin, Horsepower. Given this file and given a number N, and an origin O, print N cars that have horsepower greater than the average horsepower of all the cars from origin O. Note that the average horsepower should be computed from the cars of the given origin and not the whole dataset. The path to the dataset and the values of N and O will be passed as arguments to the program on command line.

- For example, in the dataset below:
   - Chevrolet Chevelle Malibu,US,130.0
   - Buick Skylark 320,US,165.0
   - Plymouth Satellite,US,150.0
   - Volkswagen 1131 Deluxe Sedan,Europe,46.0
   - Peugeot 504,Europe,87.0
   - Audi 100 LS,Europe,90.0

- Given N=1 and O=US, the output should be: 
  - Buick Skylark 320,US,165.0
- Given N=2 and O=US, the output should be: 
  - Buick Skylark 320,US,165.0
  - Plymouth Satellite,US,150.0
- Given N=3 and O=US, the output should be:
  - Buick Skylark 320,US,165.0
  - Plymouth Satellite,US,150.0
