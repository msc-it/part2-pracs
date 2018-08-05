P=[-0.8 -0.8 0.3 1.0 35; -0.8 0.8 -0.4 -1.0 -48];
T=[ 1 0 0 0 1];
plotpv(P ,T);
net = newp([-40 1;-1 50],1,'hardlim','learnpn');
hold on
linehandle = plotpc(net.IW{1},net.b{1});
E = 1;
net.adaptParam.passes = 3;
while (sse(E))
[net,Y,E] = adapt(net,P,T);
linehandle = plotpc(net.IW{1},net.b{1},linehandle); drawnow;
end
p = [0.7; 1.2];
a = sim(net,p);
plotpv(p,a);
circle = findobj(gca,'type','line'); set(circle,'Color','red');
hold on;
plotpv(P ,T);
plotpc(net.IW{1},net.b{1});
hold off;
axis([-2 2 -2 2]);