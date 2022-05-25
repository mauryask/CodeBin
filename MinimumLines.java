class Solution {
    
    class Stock 
    {
        int price;
        int day;
        
        Stock(int day, int price)
        {
            this.day = day;
            this.price = price;
        }
    }
    
    public int minimumLines(int[][] A) 
    {
        int n = A.length;
        
        if(n==2)
            return 1;
        
        List<Stock> stock = new ArrayList<>();
        
        for(int i=0; i<n; i++)
        {
            stock.add(new Stock(A[i][0], A[i][1]));
        }
        
        Collections.sort(stock, (a, b)->{
            return a.day - b.day;
        });
       
        int count = 0;
        double slope[] = new double[n];
        slope[0] = Integer.MIN_VALUE;
        
        //calcultaing slope
        for(int i=1; i<n; i++)
        {  
           slope[i] = (double) (stock.get(i).price - stock.get(i-1).price) / (double) (stock.get(i).day-stock.get(i-1).day);
        }
        
        for(int i=0; i<n-1; i++)
        {
            if(slope[i] != slope[i+1])
               count++;
        }
        
        return count;
    }
}
