class Solution {
	int getFactorial(int n){
		int fact = 1;
		for(int i=1; i<=n; i++){
			fact *= i;
		}		
		return fact;
	}

    void solve(int[] A, int k, StringBuilder result){
		int n = A.length;
		if(n == 0)
			return;
			
		int fact = getFactorial(n-1);
		int index = k/fact;
		k = k % fact;
		result.append(A[index]);
		
		int[] newArr = new int[n-1];
		int tempIndex = 0;
		
		for(int i=0; i<n; i++){
			if(i != index){
				newArr[tempIndex++] = A[i];
			}
		}
		
		solve(newArr, k, result);
	}

    public String getPermutation(int n, int k) {
        int[] A = new int[n];
        for(int i=1; i<=n; i++){
            A[i-1] = i;            
        }
		StringBuilder result = new StringBuilder();
	    solve(A, k-1, result);	     
        return result.toString();   
    }
}

