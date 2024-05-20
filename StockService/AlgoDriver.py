#   my plan for this algorithm is to take in a dataset and download the pre-trained neural net, run lines of the dataset on the 
#   neural net in order to predict a stocks closing price, and essentially rank the stocks, returning either the dataset or 
#   requested portions to the flask server, then, saving the dataset with the added predictions for general monitoring on the 
#   developer side


#   A possible optimization of this in the far future would be to have multiple processes separately fetch the neural net (most likely from SQL)
#   and run different sections of the database, an input could be number of processes to run, which could obviously be optimized
#   on a computer by computer basis, downloading the neural net will be expensive, therefore the number of processes almost definitely
#   should not exceed the number of CPUs

class Algo:
    def run():
        return "bullocks"