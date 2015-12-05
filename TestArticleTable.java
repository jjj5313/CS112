/** Yuanli Zhong
  * U79526308
  **/

import java.util.ArrayList;
import java.util.List;



public class TestArticleTable {
 private static String DB_PATH = "articles/";
 private Article[] articles;
 List<Article> origin = new ArrayList<Article>();
 List<Article> retrive = new ArrayList<Article>();
 
 public static void main(String[] args) {
  new TestArticleTable();
 }
 
 private void assertTrue(boolean b){
  if(!b){
   throw new RuntimeException("test fail");
  }
  
 }
 
 public TestArticleTable(){
  articles = Minipedia.getArticleList(new DatabaseIterator(DB_PATH));
  for(Article article: articles){
   origin.add(article);
  }
  testIterator();
 }
 
 private boolean aInb(Article a, List<Article> b){
  for(Article art: b){
   if(a.getTitle().equals(art.getTitle()))
    return true;
  }
  return false;
 }
 
 public void testIterator() {

  ArticleTable T = new ArticleTable();
  T.reset();
  
  String firstTitle = null;
  while(T.hasNext()) {
   Article a = T.next(); 
   
   if(firstTitle == null)
    firstTitle = a.getTitle();
      
      if(retrive.contains(a)) //retrive more than once
       throw new RuntimeException();
      retrive.add(a);
  }
  
  //origin and retrive is the same set
  for(Article originArticle:origin){
   assert(aInb(originArticle, retrive));
  }
  
  for(Article retriveaArticle:retrive){
   assert(aInb(retriveaArticle, origin));
  }
  
  //lookup
  String testTitle = "Flood";
  assertTrue(T.lookup(testTitle).getTitle().equals(testTitle));
  
  //delete
  T.delete("Flood");
  assertTrue(T.lookup(testTitle)==null);
  
  //insert
  String insertTitle = "TESTINSERT";
  assertTrue(T.lookup(insertTitle) == null);
  T.insert(new Article(insertTitle, "test insert"));
  assertTrue(T.lookup(insertTitle).getTitle().equals(insertTitle));
  
  
  //reset
  T.reset();
  assertTrue(T.hasNext());
  assertTrue(firstTitle.equals(T.next().getTitle()));
  
  System.out.println("success");
 }


}
