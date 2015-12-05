public void swapTwo(Stack S) {
  if (!S.isempty) {
    n=S.pop();
    if(!S.isEmpty) {
      m=S.pop();
      S.push(n);
      S.psuh(m);
    }
    else {
       S.push(n);
    }
  }
}
