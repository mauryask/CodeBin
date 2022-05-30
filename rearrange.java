class Solution {
    public int rearrangeCharacters(String s, String target) 
    {
       Map<Character, Integer> tMap = new HashMap<>();
       Map<Character, Integer> sMap = new HashMap<>();
       int m = s.length();
       int n = target.length();
        
        for(int i=0; i<n; i++)
        {
            char ch = target.charAt(i);
            tMap.put(ch, tMap.getOrDefault(ch,0)+1);
        }
        
        
        for(int i=0; i<m; i++)
        {
            char ch = s.charAt(i);
            
            if(tMap.containsKey(ch))
                sMap.put(ch, sMap.getOrDefault(ch,0)+1);
        }
        
        if(sMap.size() != tMap.size())
            return 0;
        if(sMap.equals(tMap))
            return 1;
        
        int sMin = Integer.MAX_VALUE;
        int tMin = Integer.MAX_VALUE;
        
        for(Integer value : sMap.values())
            sMin = Math.min(sMin, value);
        
        for(Integer value : tMap.values())
            tMin = Math.min(tMin, value); 
        
        return sMin/tMin;
    }
}
