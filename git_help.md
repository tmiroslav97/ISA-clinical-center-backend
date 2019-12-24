### GIT iz komande linije

Da git bude "svjestan" da je folder za repozitorijum  
`git init` 

Kloniranje lokalnog repozitorijuma  
`git clone /path/to/repository`

Kloniranje remote repozitorijuma  
`git clone host:/path/to/repository`

Dodavanje promjena na index (za lokalni)  
`git add *`

Komitovanje promjena (za lokalni)  
`git commit -m 'Neka poruka sta ste uradili'`

Push na remote (ovo je na default master branch)  
`git push origin`

Push na remote (na neki branch) branch_name -> ime nekog brancha  
`git push origin branch_name`

Azuriranje lokalnog repozitorijuma sa komitima koji se nalaze na remote  
`git pull`

Checkout (komanda sa kojom se mjenja trenutna grana na kojoj se nalazimo na nivou repozitorijuma)  

Pravljenje nove grane i prelazak na nju branch_name -> feature-view-za-login-page ili bugfix-view-za-login-page  
`git checkout -b branch_name`

Povratak na master granu  
`git checkout master`

Spajanje neke druge grane na granu na kojoj se nalazimo  
`git merge branch_name`

**Kako trebate sprovesti git komande prije/tokom/poslije rada**
1. Prebacivanje na master granu
    ```
    git checkout master
    git fetch origin
    git reset --hard origin/master
   ```
2. Pravljenje zeljene feature ili bugfix grane branch_name -> feature-view-za-login-page ili bugfix-view-za-login-page
    ```
    git checkout -b feature-view-za-login-page
    git checkout -b bugfix-view-za-login-page
   ```
3. Kada odradimo neki task sa trella, pa treba da komitujemo (najscese na granu koju smo otvorili)
    ```
    git add *
    git commit -m 'Uradjen task za tekst polja'
    git push origin branch_name
   ```
4. Kada zavrsimo kompletan rad na nasoj grani treba da otvorimo pull_request (dopunicu ovu tacku kasnije)

5. Merge na master granu branch_name -> feature-view-za-login-page ili bugfix-view-za-login-page
    ```
    git checkout master
    git fetch origin
    git reset --hard origin/master
    git merge branch_name
   ```
   
   Linkovi:  
   https://www.atlassian.com/git/tutorials/comparing-workflows/feature-branch-workflow
   

