% 1. Set training input and target vectors
%    and optionally plot them
P = -1:.1:1;
T = [ +0.8 +0.5 -0.3 -0.9 +0.8 +0.6 -0.4 ...
      +0.5 -0.3 +0.6 +0.8 +0.5 -0.3 +0.2 ...
      +0.7 -0.6 +0.8 -0.5 -0.3 +0.1 -0.1]; 
subplot(3, 1, 1);
plot(P,T,'+');
title('Training Vectors');
xlabel('Input Vector P');
ylabel('Target Vector T');

% 2. Use a transfer function to calculate outputs from network input
%    OPTIONAL STEP
% radbas is a transfer function. Transfer functions
% calculate a layer's output from its network input.
p = -3:.1:3;
a = radbas(p);
a2 = radbas(p-1.5);
a3 = radbas(p+2);
a4 = a + a2*2 + a3*1; 
subplot(3, 1, 2);
title('Radial Basis Transfer Function'); 
xlabel('Input p');
ylabel('Output a');
hold on;
    plot(p,  a, 'b-');
    plot(p, a2, 'b--');
    plot(p, a3, 'b:');
    plot(p, a4, 'm-');
hold off;
legend({'a','a2', 'a3', 'a4'});

% 3. Create a radial basis network
eg = 0.015; % sum-squared error goal 
sc = 0.06; % spread constant
net = newrb(P, T, eg, sc);
subplot(3, 1, 3);
title('Weighted Sum of Radial Basis Transfer Functions'); 
X = -1:.01:1; % input vector (same as P but with a smaller interval)
Y = sim(net,X);
hold on;
    plot(P, T, '+');
    plot(X, Y);
hold off;
legend({'Target T','Output Y'});