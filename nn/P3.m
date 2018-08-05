P=[-0.8 -0.8 0.3 1.0 0.7; -0.8 0.8 -0.4 -1.0 -0.7];
T = [1 0 1 0 1];
plotpv(P ,T);
net = newp([-40 1;-1 50],1);
hold on
plotpv(P ,T);
linehandle = plotpc(net.IW{1},net.b{1}); 
net.adaptParam.passes = 3; 
linehandle = plotpc(net.IW{1},net.b{1});
for a = 1:25
    [net,Y,E] = adapt(net,P,T);
    linehandle = plotpc(net.IW{1},net.b{1},linehandle); 
    drawnow; 
end

