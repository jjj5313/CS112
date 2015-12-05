/** Yuanli Zhong
  * U79526308
  **/

public class TestTermFrequencyTable {
 
 public TestTermFrequencyTable(){
  testSameTerms();
  testNoCommon();
  testSameCommon();
  System.out.println("success");
  
 }
 public static void main(String[] args) {
  new TestTermFrequencyTable();
  
 }
 
 private void assertTrue(boolean b){
  if(!b){
   throw new RuntimeException("test fail");
  }
  
 }

 public void testSameTerms() {
  
  TermFrequencyTable table = new TermFrequencyTable();
  String s = "abcdefg";
  
  for(int doc=0;doc<2;++doc){
   for(int i=0;i<s.length();++i){
    table.insert(s.substring(i, i+1), doc);
   }
  }
  
  double TORRENT = 1e-8;
  assertTrue(table.cosineSimilarity()-1.0<TORRENT);
  
 }
 
 public void testNoCommon(){
  
  TermFrequencyTable table = new TermFrequencyTable();
  String s1 = "abcdefg";
  String s2 = "hijklmn";
  
  
  for(int i=0;i<s1.length();++i){
   table.insert(s1.substring(i, i+1), 0);
  }
  
  for(int i=0;i<s2.length();++i){
   table.insert(s2.substring(i, i+1), 1);
  }
  
  double TORRENT = 1e-8;
  assertTrue(table.cosineSimilarity()-0<TORRENT);
  
 }
 
 
 public void testSameCommon(){
  
  TermFrequencyTable table = new TermFrequencyTable();
  String s1 = "aaa";
  String s2 = "aab";
  
  
  for(int i=0;i<s1.length();++i){
   table.insert(s1.substring(i, i+1), 0);
  }
  
  for(int i=0;i<s2.length();++i){
   table.insert(s2.substring(i, i+1), 1);
  }
  
  double TORRENT = 1e-8;
  double expect = 3.0*2.0 / Math.sqrt(3.0*3.0) / Math.sqrt(2.0*2.0 + 1.0*1.0);
  assertTrue(Math.abs(expect-table.cosineSimilarity())<TORRENT);
  
 }


}
