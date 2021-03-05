package Assign_1;

public class Chicken implements Runnable {

    private Resources res;  // get the ingredients from resource class (to be received from the main method)
    private int numChicken; //how many chicken dishes we want (comes from main method)
    private int reportProgressAfter;
    private int howManyCooked=0;
    public Chicken(Resources res,int numChicken,int reportProgressAfter){
        this.res=res;
        this.numChicken=numChicken;
        this.reportProgressAfter=reportProgressAfter;
    }

    public void run() {

        int i=1;
        System.out.println("Starting to cook Chicken ");
        while (i<=numChicken) {

            //step 1
            res.saltMallet.lock();
            res.saltMallet.unlock();

            //step 2
            res.eggs.lock();
            res.flour.lock();
            res.eggs.unlock();
            res.flour.unlock();

            //step 3
            res.sink.lock();
            res.sink.unlock();

            //step 4
            res.stove.lock();
            res.stove.unlock();

            // increase the tally of the total number of dishes made
            res.tally.lock();
            res.totalDishesMade++;
            res.tally.unlock();

            //check if we have to report progress, if yes then lock the output and display (i)th number
            if(i%reportProgressAfter==0){
                res.output.lock();
                System.out.println(i+" Chicken dishes made");
                res.output.unlock();
            }
            howManyCooked++;
            i++;

        }


        System.out.println("Chickens are cooked: total "+ howManyCooked + " Chicken dishes were cooked");
    }
}
