x = 0:1:10;
mf1 = trapmf(x,[1 5 7 8]); 
plot(x,mf1,'LineWidth',3);
x1 = defuzz(x,mf1,'centroid')
h1 = line([x1 x1],[-0.2 1.2]);
t1 = text(x1,-0.2,' centroid','FontWeight','normal'); 
x2 = defuzz(x,mf1,'bisector')
gray = 0.7*[1 1 1];
set([h1 t1],'Color',gray)
h2 = line([x2 x2],[-0.4 1.2]);
t2 = text(x2,-0.4,' bisector','FontWeight','bold'); 
x3 = defuzz(x,mf1,'mom')
x4 = defuzz(x,mf1,'som')
x5 = defuzz(x,mf1,'lom')
set([h2 t2],'Color',gray)
h3 = line([x3 x3],[-0.7 1.2]);
t3 = text(x3,-0.7,' MOM','FontWeight','bold'); 
h4 = line([x4 x4],[-0.8 1.2]);
t4 = text(x4,-0.6,' SOM','FontWeight','bold'); 
h5 = line([x5 x5],[-0.6 1.2]);
t5 = text(x5,-0.6,' LOM','FontWeight','bold'); 
set([h3 t3 h4 t4 h5 t5],'Color',gray)
set([h1 t1],'Color','red')